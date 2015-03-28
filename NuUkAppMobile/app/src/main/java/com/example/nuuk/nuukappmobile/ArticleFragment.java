package com.example.nuuk.nuukappmobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Kody on 27/03/2015.
 */
public class ArticleFragment extends Fragment {
    public static final String ARG_ARTICLES_NUMBER = "articles_number";
    public ArticleFragment(){
        //Constructo obligatorio
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceBundle){
        View rootView = inflater.inflate(R.layout.fragment_article, container,false);
        int i = getArguments().getInt(ARG_ARTICLES_NUMBER);
        String article = getResources().getStringArray(R.array.Tags)[i];
        getActivity().setTitle(article);
        TextView headline = (TextView)rootView.findViewById(R.id.headline);
        headline.append(" " + article);

        return rootView;
    }
}

