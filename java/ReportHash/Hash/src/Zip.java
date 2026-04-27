import java.io.BufferedReader;
import java.io.FileReader;

public class Zip {

    Area[] postnr;
    int max = 10000;

    public class Area {
        String name;
        String code;
        int population;

        public Area(String code, String name, int population){
            this.code = code;
            this.name = name;
            this.population = population;
        }
    }
    public Zip(String file) {
        this.postnr = new Area[this.max];
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
        String line;
        int i = 0;
        while ((line = br.readLine()) != null && i < this.max) {
            String[] row = line.split(",");
            postnr[i++] = new Area(row[0], row[1], Integer.valueOf(row[2]));
        }
        this.max = i;
        } catch (Exception e) {
        System.out.println(" file " + file + " not found");
        }
    }

    public String lookupCode(String find){
        for(int i = 0; i < postnr.length; i++){
            if(postnr[i] != null && postnr[i].code.equals(find)){
                return postnr[i].name;
            }
        }
        return null;
}

    public static void main(String[] args) {
        Zip file = new Zip("postnummer.csv");

        System.out.println(file.lookupCode("000 00"));
    }

}
    
