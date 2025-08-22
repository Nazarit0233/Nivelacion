import java.util.Scanner;

public class Contador {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Entrada de la palabra
        System.out.print("Ingrese una palabra en minúsculas (sin números ni símbolos): ");
        String palabra = scanner.nextLine();

        int vocales = 0;
        int consonantes = 0;

        // Recorremos cada carácter de la palabra
        for (int i = 0; i < palabra.length(); i++) {
            char c = palabra.charAt(i);

            // Verificamos si es vocal
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                vocales++;
            } else { 
                // Si no es vocal, es consonante (porque la entrada es válida según el enunciado)
                consonantes++;
            }
        }

        // Resultados
        System.out.println("Número de vocales: " + vocales);
        System.out.println("Número de consonantes: " + consonantes);

        scanner.close();
    }
}
