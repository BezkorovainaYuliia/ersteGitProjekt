import java.time.YearMonth;
import java.util.Calendar;
import java.util.Scanner;


public class Main {
    //Static CONSTANTEN
    public static final String[] WOCHE = {"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};
    public static final String[] MONATEN = {"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
                                            "September", "October", "November", "December"};
    public static void main(String[] args) {

        System.out.println("Wähl das Jahr: "   );
        Scanner sc = new Scanner(System.in);
        int jahr = sc.nextInt();

        System.out.println("Spaltenbreite ist ");
        int grossBreite = sc.nextInt();
        String breite = "\t";
         for (int i = 0; i < grossBreite; i++){
             breite += breite;
         }

        //Bestimmung, ob das Jahr ein Schaltjahr ist
        System.out.println("Ist das Jahr ein Schaltjahr \t" + (istSchaltjahr(jahr) ? "ja" : "nein"));

        Calendar calendar = Calendar.getInstance();


        for (int iMonaten = 0; iMonaten < 12; iMonaten++){

            calendar.set(jahr,iMonaten,1);

            YearMonth yearMonth = YearMonth.of(jahr, iMonaten + 1);

            int tagenInMonat = yearMonth.lengthOfMonth();

            System.out.println(MONATEN[iMonaten]);
            printWocheName(breite);

            int startMonatWochetag = calendar.get(Calendar.DAY_OF_WEEK) - 2;
            if (startMonatWochetag == -1) startMonatWochetag = 6;

            int tag = 1;
            for (int j = 0; j < startMonatWochetag; j++){
                System.out.print(breite);
            }
            for(int i = startMonatWochetag; i < 7; i++){

                System.out.print(tag + breite);
                tag++;
            }

            System.out.println();
            int counter = 1;
            for (; tag <= tagenInMonat; tag++){
                if(counter == 8) {
                    counter = 1;
                    System.out.println();
                }
                if(counter <= 7){
                    System.out.print(tag + breite);
                }
                counter++;
            }
            System.out.println();
        }
    }

    public static void printWocheName(String breite) {
        for (String wocheTag :  WOCHE){
            System.out.print(wocheTag + breite);
        }
        System.out.println();
    }

    public static boolean istSchaltjahr(int jahr){
        YearMonth yearMonth = YearMonth.of(jahr, 2);
        int tagenInMonat = yearMonth.lengthOfMonth();
        return tagenInMonat == 29;
    }
}
