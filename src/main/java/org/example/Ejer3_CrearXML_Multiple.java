package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

/**
 * Modifica el programa del Ejercicio 1 para que genere un fichero XML llamado frutas.xml que
 * contenga información de varias frutas. La estructura debe ser similar a:
 */
public class Ejer3_CrearXML_Multiple {
    public static void main(String[] args) {
        try {
            // Crear un documento XML
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            // Crear el elemento raíz
            Element raiz = doc.createElement("frutas");
            doc.appendChild(raiz);

            // Crear elemento fruta1
            Element fruta1 = doc.createElement("fruta");
            raiz.appendChild(fruta1);

            // Crear el elemento nombre
            Element nombre1 = doc.createElement("nombre");
            nombre1.appendChild(doc.createTextNode("Manzana"));
            fruta1.appendChild(nombre1);

            // Crear el elemento color
            Element color1 = doc.createElement("color");
            color1.appendChild(doc.createTextNode("Rojo"));
            fruta1.appendChild(color1);

            // Crear el elemento cantidad
            Element cantidad1 = doc.createElement("cantidad");
            cantidad1.appendChild(doc.createTextNode("10"));
            fruta1.appendChild(cantidad1);

            // Crear elemento fruta2
            Element fruta2 = doc.createElement("fruta");
            raiz.appendChild(fruta2);

            // Crear el elemento nombre
            Element nombre2 = doc.createElement("nombre");
            nombre2.appendChild(doc.createTextNode("Banana"));
            fruta2.appendChild(nombre2);

            // Crear el elemento color
            Element color2 = doc.createElement("color");
            color2.appendChild(doc.createTextNode("Amarillo"));
            fruta2.appendChild(color2);

            // Crear el elemento cantidad
            Element cantidad2 = doc.createElement("cantidad");
            cantidad2.appendChild(doc.createTextNode("5"));
            fruta2.appendChild(cantidad2);

            // Escribir el contenido del documento en un fichero XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            //XML esté bien formateado con saltos de línea y sangría
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("frutas.xml"));
            transformer.transform(source, result);

            System.out.println("Documento XML creado correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}