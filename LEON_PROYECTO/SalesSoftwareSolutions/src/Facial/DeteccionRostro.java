package Facial;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.highgui.Highgui;
import org.opencv.objdetect.CascadeClassifier;

/**
 *
 * @author Shary
 */
// Detects faces in an image, draws boxes around them, and writes the results
// to "faceDetection.png".
class DetectFace {

    public void run() {
        System.out.println("\nRunning DetectFace");

        // Create a face detector from the cascade file in the resources
        // directory.
        // La variable urlxml contiene la ubicacion donde se encuentra el
        // archivo .XML que realiza la deteccion.
        String urlxml = getClass().getResource(
                "/Facial/Deteccion/lbpcascade_frontalface.xml").getPath();
        // La variable urlimg contiene la ubicacion de la imagen a analizar.
        String urlimg = getClass().getResource("/Facial/Deteccion/lena.png").getPath();

        // Analiza las url obtenidas y reemplaza los caracteres invalidos.
        if (urlxml.startsWith("/", 0) && urlimg.startsWith("/", 0)) {
            urlxml = urlxml.replaceFirst("/", "");
            urlxml = urlxml.replace("%20", " ");
            urlimg = urlimg.replaceFirst("/", "");
            urlimg = urlimg.replace("%20", " ");
        }

        // Crea los objetos necesarios para manipular el archivo de la detección
        // de rostros y de la imagen.
        CascadeClassifier faceDetector1 = new CascadeClassifier(urlxml);
        Mat image1 = Highgui.imread(urlimg);

        // Detect faces in the image.
        // MatOfRect is a special container class for Rect.
        MatOfRect faceDetections = new MatOfRect();
        faceDetector1.detectMultiScale(image1, faceDetections);

        System.out.println(String.format("Detected %s faces",
                faceDetections.toArray().length));

        // Draw a bounding box around each face.
        for (Rect rect : faceDetections.toArray()) {
            Core.rectangle(image1, new Point(rect.x, rect.y), new Point(rect.x
                    + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
        }

        // Save the visualized detection.
        String filename = "faceDetection.png";
        System.out.println(String.format("Writing %s", filename));
        Highgui.imwrite(filename, image1);
        
    }
}

public class DeteccionRostro {

    public static void main(String[] args) {
        System.out.println("Hello, OpenCV");
        // Load the native library.
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        new DetectFace().run();
    }
}
