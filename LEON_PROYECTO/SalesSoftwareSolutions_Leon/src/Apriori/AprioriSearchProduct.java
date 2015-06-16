/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Apriori;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.swing.JList;

/**
 *
 * @author Izzy-Izumi
 */
public class AprioriSearchProduct {

    public String listado="",listado1="";

    public AprioriSearchProduct(String numeroTran, String transDatos) {
        int n, min;
        int max = 0;
        int maxLen = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//AprioriSearchProduct ap=new AprioriSearchProduct(numeroTran,transDatos);
        //System.out.print("Ingrese numero de transacciones ");
        n = Integer.parseInt(numeroTran);
        String[] trans = new String[n];
        trans = transDatos.split(",");
        //System.out.print("Ingrese transacciones ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < trans[i].length(); j++) {
                if (Integer.parseInt("" + trans[i].charAt(j)) > max) {
                    max = Integer.parseInt("" + trans[i].charAt(j));
                }
            }
        }
        //System.out.print("Soporte minimo: ");
        min = 1;
        String str = "";
        for (int i = 1; i <= max; i++) {
            str += "" + i;
        }
        ArrayList al;
        max = 0;
        ArrayList<String> allItems = new ArrayList<String>();
        for (int i = 1; i <= str.length(); i++) {
            al = permutationsOf(str.substring(0, i));
            for (int j = 0; j < al.size(); j++) {
                allItems.add("" + al.get(j));
                if (new String("" + al.get(j)).length() > max) {
                    max = new String("" + al.get(j)).length();
                }
            }
        }
        ArrayList<String> arr[] = new ArrayList[max];
        for (int i = 0; i < max; i++) {
            arr[i] = new ArrayList<String>();
        }
        for (int j = 0; j < allItems.size(); j++) {
            arr[(allItems.get(j).length()) - 1].add(allItems.get(j));
        }
        int count[] = new int[allItems.size()], l = 0;
        int flag = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                for (int k = 0; k < n; k++) {
                    for (int m = 0; m < (arr[i].get(j).length()); m++) {
                        if (trans[k].indexOf(arr[i].get(j).charAt(m)) == -1) {
                            flag = 1;
                        }
                    }
                    if (flag == 0) {
                        count[l] += 1;
                    }
                    flag = 0;
                }
                l++;
            }
        }
        int k = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                if (count[k] >= min) {
                    if (maxLen < arr[i].get(j).length()) {
                        maxLen = arr[i].get(j).length();
                    }
                }
                k++;
            }
        }
        k = 0;
        System.out.println("Conjunto de elementos frecuentes: ");
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < arr[i].size(); j++) {
                if (count[k] >= min) {
                    System.out.println(arr[i].get(j) + " " + count[k]);
                    listado+=arr[i].get(j)+",";
                    listado1+=count[k]+",";
                }
                k++;
            }
        }
    }

    ArrayList<String> permutationsOf(String str) {
        String temp = "", match = "";
        int flag = 0;
        ArrayList<String> result = new ArrayList<String>();
        if (str.length() == 1) {
            result.add(str);
            return result;
        } else {
            char ch = str.charAt(0);
            String rem = str.substring(1);
            ArrayList<String> a = permutationsOf(rem);
            for (String p : a) {
                result.add(p);
                ArrayList addn = insertItems(ch, p);
                for (int i = 0; i < addn.size(); i++) {
                    match = (String) addn.get(i);
                    flag = 0;
                    for (int j = i + 1; j < addn.size(); j++) {
                        temp = (String) addn.get(j);
                        for (int k = 0; k < temp.length(); k++) {
                            for (int l = 0; l < match.length(); l++) {
                                if (temp.charAt(k) == match.charAt(l)) {
                                    flag++;
                                }
                            }
                        }
                        if (flag >= match.length()) {
                            addn.remove(j);
                        }
                    }
                }
                result.addAll(addn);
            }
            return (result);
        }
    }

    ArrayList<String> insertItems(char c, String str) {
        String temp = "", match = "";
        int flag = 0;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i <= str.length(); i++) {
            res.add(str.substring(0, i) + c + str.substring(i));
        }
        for (int i = 0; i < res.size(); i++) {
            match = (String) res.get(i);
            flag = 0;
            for (int j = i + 1; j < res.size(); j++) {
                temp = (String) res.get(j);
                for (int k = 0; k < temp.length(); k++) {
                    for (int l = 0; l < match.length(); l++) {
                        if (temp.charAt(k) == match.charAt(l)) {
                            flag++;
                        }
                    }
                }
                if (flag >= match.length()) {
                    res.remove(j);
                }
            }
        }
        return (res);
    }

}
