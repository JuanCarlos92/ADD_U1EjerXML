package org.example;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.Scanner;

/**
 * Crea un programa que lea el fichero frutas.xml, permita al usuario modificar el color o la cantidad de una fruta
 * específica (por ejemplo, cambiar la cantidad de la primera fruta a “20”), y luego sobrescriba el fichero frutas.xml
 * con la información actualizada. Asegúrate de manejar correctamente la estructura XML al realizar las modificaciones.
 */
public class Ejer5_ModificarXML {
    public static void main(String[] args) {
        try {
            // Cargar el documento XML
            File archivo = new File("frutas.xml");

            if (!archivo.exists()) {
                System.out.println("El archivo frutas.xml no existe.");
                return;
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(archivo);

            // Normalizar el documento XML
            doc.getDocumentElement().normalize();

            NodeList listaFrutas = doc.getElementsByTagName("fruta");

            for (int i = 0; i < listaFrutas.getLength(); i++) {
                Element fruta = (Element) listaFrutas.item(i);
                System.out.println(i + 1 + "º Fruta. Nombre: " + fruta.getElementsByTagName("nombre").item(0).getTextContent());
            }

            Scanner sc = new Scanner(System.in);
            System.out.print("Elige el número de la fruta que deseas modificar: ");
            int respuesta = sc.nextInt() - 1;

            if (respuesta >= 0 && respuesta < listaFrutas.getLength()) {
                Element frutaSeleccionada = (Element) listaFrutas.item(respuesta);

                // Mostrar las opciones de modificación
                System.out.println("Has seleccionado la fruta : " + frutaSeleccionada.getElementsByTagName("nombre").item(respuesta).getTextContent());
                System.out.println("1. Modificar color");
                System.out.println("2. Modificar cantidad");
                System.out.print("Elige la opción a modificar (1/2): ");

                int respuestaParaModificar = sc.nextInt();
                sc.nextLine();

                if (respuestaParaModificar == 1) {
                    System.out.print("Introduce el nuevo color: ");
                    String nuevoColor = sc.nextLine();
                    frutaSeleccionada.getElementsByTagName("color").item(0).setTextContent(nuevoColor);
                    System.out.println("Color modificado a " + nuevoColor);

                } else if (respuestaParaModificar == 2) {
                    System.out.print("Introduce la nueva cantidad: ");
                    String nuevaCantidad = sc.nextLine();
                    frutaSeleccionada.getElementsByTagName("cantidad").item(0).setTextContent(nuevaCantidad);
                    System.out.println("cantidad modificada a " + nuevaCantidad);

                } else {
                    System.out.println("Opción no válida.");
                    return;
                }

                // Sobrescribir el archivo XML
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();

                //XML esté bien formateado con saltos de línea y sangría
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");


                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(archivo);
                transformer.transform(source, result);

                System.out.println("El archivo frutas.xml ha sido actualizado.");

            } else {
                System.out.println("Opción no válida.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
