// Autor:    Alejandro Serna Collazos
// Abstract: Clase principal interactiva con menú numérico

package tareaa;

import java.util.Scanner;

public class Ejemplo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del cliente: ");
        String nombreCliente = scanner.nextLine();
        System.out.print("Esta el cliente exento de 4x1000? (true/false): ");
        boolean exento4x1000 = scanner.nextBoolean();
        scanner.nextLine();
        Cliente cliente = new Cliente(nombreCliente, exento4x1000);
        System.out.print("Ingrese el saldo inicial de la cuenta: ");
        int saldoInicial = scanner.nextInt();
        scanner.nextLine();
        Tareaa cuenta = new Tareaa(cliente, saldoInicial);

        boolean salir = false;      // Menú interactivo
        while (!salir) {
            System.out.println("\n*** Menu de operaciones ***");
            System.out.println("1. Ver saldo");
            System.out.println("2. Realizar consignacion");
            System.out.println("3. Realizar retiro");
            System.out.println("4. Realizar transferencia");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opcion: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Ver saldo
                    System.out.println("El saldo de la cuenta de " + cliente.getNombre() + " es: " + cuenta.getSaldo());
                    break;

                case 2:
                    // Realizar consignación
                    System.out.print("Ingrese el monto a consignar: ");
                    int montoConsignacion = scanner.nextInt();
                    scanner.nextLine();
                    boolean consignacionExitosa = cuenta.consignacion(montoConsignacion);
                    if (consignacionExitosa) {
                        System.out.println("Consignacion realizada con exito.");
                    } else {
                        System.out.println("Error en la consignacion. El monto debe ser positivo.");
                    }
                    break;

                case 3:
                    // Realizar retiro
                    System.out.print("Ingrese el monto a retirar: ");
                    int montoRetiro = scanner.nextInt();
                    System.out.println("Seleccione el tipo de cajero:");
                    System.out.println("1. Mismo banco");
                    System.out.println("2. Otro banco");
                    System.out.println("3. Oficina mismo banco");
                    System.out.println("4. Oficina otro banco");

                    int tipoCajeroSeleccionado = scanner.nextInt();
                    scanner.nextLine();

                    // Determinar el tipo de cajero seleccionado
                    String tipoCajero = "";
                    switch (tipoCajeroSeleccionado) {
                        case 1: tipoCajero = "mismoBanco"; break;
                        case 2: tipoCajero = "otroBanco"; break;
                        case 3: tipoCajero = "oficinaMismoBanco"; break;
                        case 4: tipoCajero = "oficinaOtroBanco"; break;
                        default: 
                            System.out.println("Opcion no valida. Seleccionando 'mismoBanco' por defecto.");
                            tipoCajero = "mismoBanco";
                            break;
                    }

                    boolean retiroExitoso = cuenta.retiro(montoRetiro, tipoCajero);
                    if (retiroExitoso) {
                        System.out.println("Retiro realizado con exito.");
                    } else {
                        System.out.println("No hay suficiente saldo para realizar el retiro.");
                    }
                    break;

                case 4:
                    // Realizar transferencia
                    System.out.print("Ingrese el nombre del beneficiario: ");
                    scanner.nextLine();
                    String nombreBeneficiario = scanner.nextLine();
                    System.out.print("Ingrese el saldo inicial de la cuenta del beneficiario: ");
                    int saldoBeneficiario = scanner.nextInt();
                    System.out.print("Es una transferencia interbancaria? (true/false): ");
                    boolean esInterbancaria = scanner.nextBoolean();
                    scanner.nextLine(); 
                    
                    // Crear la cuenta del beneficiario
                    Cliente beneficiario = new Cliente(nombreBeneficiario, false); // Suponemos que el beneficiario no está exento de 4x1000
                    Tareaa cuentaBeneficiario = new Tareaa(beneficiario, saldoBeneficiario);

                    System.out.print("Ingrese el monto a transferir: ");
                    int montoTransferencia = scanner.nextInt();
                    boolean transferenciaExitosa = cuenta.transferencia(montoTransferencia, cuentaBeneficiario, esInterbancaria);
                    if (transferenciaExitosa) {
                        System.out.println("Transferencia realizada con exito.");
                        System.out.println("Saldo de la cuenta de " + cliente.getNombre() + ": " + cuenta.getSaldo());
                        System.out.println("Saldo de la cuenta de " + beneficiario.getNombre() + ": " + cuentaBeneficiario.getSaldo());
                    } else {
                        System.out.println("No hay suficiente saldo para realizar la transferencia.");
                    }
                    break;

                case 5:
                    // Salir
                    salir = true;
                    System.out.println("Gracias por utilizar el sistema bancario!");
                    break;

                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }
        }
    }
}