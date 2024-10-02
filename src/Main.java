import javax.swing.JOptionPane;

interface VätskeBehov {
    String vätskeInfo();  // Metod för vätskeinfon
}

abstract class Växt implements VätskeBehov {
    private String namn;
    private double höjd;

    public Växt(String namn, double höjd) {
        this.namn = namn;
        this.höjd = höjd;
    }

    public String getNamn() {
        return namn;
    }

    public double getHöjd() {
        return höjd;
    }

    public abstract String vätskeInfo();
}

class Palm extends Växt {
    private static final double VattenPerMeter = 0.5;
    private static final String vätsketyp = "kranvatten";

    public Palm(String namn, double höjd) {
        super(namn, höjd);
    }

    @Override
    public String vätskeInfo() {
        double vattenMängd = VattenPerMeter * getHöjd();
        return getNamn() + " behöver " + vattenMängd + " liter " + vätsketyp + " per dag.";
    }
}

class KöttätandeVäxt extends Växt {
    private static final double basvärde = 0.1;
    private static final double extraPerMeter = 0.2;
    private static final String vätskeTyp = "proteindryck";

    public KöttätandeVäxt(String namn, double höjd) {
        super(namn, höjd);
    }

    @Override
    public String vätskeInfo() {
        double vattenMängd = basvärde + (extraPerMeter * getHöjd());
        return getNamn() + " behöver " + vattenMängd + " liter " + vätskeTyp + " per dag.";
    }
}

class Kaktus extends Växt {
    private static final double vattenMängd = 0.02;
    private static final String vätskeTyp = "mineralvatten";

    public Kaktus(String namn, double höjd) {
        super(namn, höjd);
    }

    public String vätskeInfo() {
        return getNamn() + " behöver " + vattenMängd + " liter " + vätskeTyp + " idag.";
    }
}

class Växthotell {
    private static final String fråga = "Vilken växt ska få vätska?";
    private static final String felmeddelande = "Växten finns inte på hotellet.";

    public static void main(String[] args) {
        Växt[] växter = {
                new Kaktus("Igge", 0.2),
                new Palm("Laura", 5.0),
                new KöttätandeVäxt("Meatloaf", 0.7),
                new Palm("Olof", 1.0)
        };

        String växtNamn = JOptionPane.showInputDialog(fråga);

        boolean hittad = false;
        for (Växt växt : växter) {
            if (växt.getNamn().equalsIgnoreCase(växtNamn)) {
                JOptionPane.showMessageDialog(null, växt.vätskeInfo());
                hittad = true;
                break;
            }
        }

        if (!hittad) {
            JOptionPane.showMessageDialog(null, felmeddelande);
        }
    }
}
