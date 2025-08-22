import java.util.Scanner;

public class InvertirCadena {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada del usuario
        System.out.print("Ingrese una cadena de texto: ");
        String texto = scanner.nextLine();

        // Opción 1: Usando StringBuilder (más simple y eficiente)
        String invertida = new StringBuilder(texto).reverse().toString();

        // Opción 2: Usando un bucle manual (para practicar)
        String invertidaManual = "";
        for (int i = texto.length() - 1; i >= 0; i--) {
            invertidaManual += texto.charAt(i);
        }

        // Salida
        System.out.println("Cadena invertida (con StringBuilder): " + invertida);
        System.out.println("Cadena invertida (con bucle): " + invertidaManual);

        scanner.close();
    }
}
