//Autor:    Alejandro Serna Collazos
//Abstract: Manejo de operaciones basicas en github, creacion de codigo para el manejo hipotetico de un sistema bancario en Colombia

package tareaa;

public class Tareaa {
    private Cliente titular;
    private int saldo;
    
    private static final int TARIFA_RETIRO_MISMO_BANCO = 2500;          // Tarifas fijas para las transacciones segun su tipo
    private static final int TARIFA_RETIRO_OTRO_BANCO = 7500;
    private static final int TARIFA_RETIRO_OFICINA_MISMO_BANCO = 0;
    private static final int TARIFA_RETIRO_OFICINA_OTRO_BANCO = 5000;
    private static final int TARIFA_TRANSFERENCIA_MISMO_BANCO = 0;
    private static final int TARIFA_TRANSFERENCIA_INTERBANCARIA = 5000;
    private static final double IMPUESTO_4X1000 = 0.004;                // 0.4%

    public Tareaa(Cliente titular, int saldoInicial) {
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public int getSaldo() {
        return saldo;
    }

    private boolean aplicar4x1000(double monto) {               // Método para verificar si la transacción supera el umbral y aplica el 4x1000
        if (monto > 11800000 || titular.esExento4x1000()) {
            return true;                                        // Aplica el 4x1000 si la transacción es mayor a 11.8 millones o si está exento
        }
        return false;
    }

    private int calcularImpuesto4x1000(double monto) {          // Método para calcular el impuesto 4x1000
        return (int) (monto * IMPUESTO_4X1000);
    }

    public boolean retiro(int monto, String tipoCajero) {       // Método para realizar un retiro
        int tarifa = obtenerTarifaDeRetiro(tipoCajero);
        
        if (saldo >= monto + tarifa) {
            saldo -= (monto + tarifa);

            if (aplicar4x1000(monto)) {
                saldo -= calcularImpuesto4x1000(monto);
            }

            return true;    // Retiro exitoso
        } else {
            return false;   // No hay suficiente saldo
        }
    }

    private int obtenerTarifaDeRetiro(String tipoCajero) {      // Método para obtener la tarifa según el tipo de cajero
        switch (tipoCajero) {
            case "mismoBanco":
                return TARIFA_RETIRO_MISMO_BANCO;
            case "otroBanco":
                return TARIFA_RETIRO_OTRO_BANCO;
            case "oficinaMismoBanco":
                return TARIFA_RETIRO_OFICINA_MISMO_BANCO;
            case "oficinaOtroBanco":
                return TARIFA_RETIRO_OFICINA_OTRO_BANCO;
            default:
                return TARIFA_RETIRO_OTRO_BANCO;                // Tarifa desconocida
        }
    }

    public boolean transferencia(int monto, Tareaa cuentaDestino, boolean esInterbancaria) {    // Método para realizar una transferencia
        int tarifa = esInterbancaria ? TARIFA_TRANSFERENCIA_INTERBANCARIA : TARIFA_TRANSFERENCIA_MISMO_BANCO;
        
        if (saldo >= monto + tarifa) {
            saldo -= (monto + tarifa);

            if (aplicar4x1000(monto)) {
                saldo -= calcularImpuesto4x1000(monto);
            }

            cuentaDestino.saldo += monto;
            return true;            // Transferencia exitosa
        } else {
            return false;           // No hay suficiente saldo
        }
    }

    public boolean consignacion(int monto) {    // Método para consignar dinero
        if (monto > 0) {
            saldo += monto;
            return true;        // Consignación exitosa
        }
        return false;           // Error en consignación
    }
}