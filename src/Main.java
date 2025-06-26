import java.time.YearMonth;
import java.util.Calendar;
import java.util.Scanner;


public class Main {
    //Static CONSTANTEN
    public static final String[][] WOCHE = {{"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"},
                                            {"Mo", "Tu", "We", "Th", "Fr", "Sa", "Su"},
                                            {"Пн", "Вт", "Ср", "Чт", "Пт", "Сб", "Нд"}};

    public static final String[][] MONATEN = {{"Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August", "September", "October", "November", "December"},
            {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"},
            {"Січень", "Лютий", "Березень", "Квітень", "Травень", "Червень", "Липень", "Серпень", "Вересень", "Жовтень", "Листопад", "Грудень"}};

    public static final String[] JAHR_MESSAGE = {"Wähl das Jahr: ", "Choose the year: ", "Оберіть рік: "};

    public static final String[] BREITE_MESSAGE = {"Spaltenbreite ist ", "Column width is ", "Ширина стовпця дорівнює "};

    public static final String[] SCHALTJAHR_MESSAGE = {"Ist das Jahr ein Schaltjahr? \t", "Is the year a leap year?  \t", "Чи є рік високосним? \t"};

    public static final String[] JA_ANTWORT = {"JA", "YES", "ТАК"};
    public static final String[] NEIN_ANTWORT = {"NEIN", "NO", "НІ"};

    public static final String[] ERSTE_JANUAR = {"Den 1. Januar des Jahres ist ", "January 1 of the year is ", "1 січня року - це "};

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Wähle eine Sprache (1 - Deutsch, 2 - English, 3 - Ukrainisch)");
        int sprache = sc.nextInt() - 1;
        if (sprache > 3){
            System.out.println("Es gibt ein Fehler. Weiter spreche ich auf Deutsch");
            sprache = 0;
        }
        //Wahl des Jahr
        System.out.println(JAHR_MESSAGE[sprache]);
        int jahr = sc.nextInt();

        //Breite
        System.out.println(BREITE_MESSAGE[sprache]);
        int grossBreite = sc.nextInt();
        String breite = "\t";
         for (int i = 0; i < grossBreite; i++){
             breite += breite;
         }

        //Bestimmung, ob das Jahr ein Schaltjahr ist
        System.out.println(SCHALTJAHR_MESSAGE[sprache] + (istSchaltjahr(jahr) ? JA_ANTWORT[sprache] : NEIN_ANTWORT[sprache]));

        Calendar calendar = Calendar.getInstance();

        //Berechnung des Wochentags für den 1. Januar des Jahres
        calendar.set(jahr,Calendar.JANUARY,1);
        int startMonatWochetag = calendar.get(Calendar.DAY_OF_WEEK) - 2;
        if (startMonatWochetag == -1) startMonatWochetag = 6;
        System.out.println(ERSTE_JANUAR[sprache] + WOCHE[sprache][startMonatWochetag]);


        for (int iMonaten = 0; iMonaten < 12; iMonaten++){

            calendar.set(jahr,iMonaten,1);

            YearMonth yearMonth = YearMonth.of(jahr, iMonaten + 1);

            int tagenInMonat = yearMonth.lengthOfMonth();

            System.out.println(MONATEN[sprache][iMonaten]);
            printWocheName(breite, sprache);

            startMonatWochetag = calendar.get(Calendar.DAY_OF_WEEK) - 2;
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

    public static void printWocheName(String breite, int sprache) {
        for (String wocheTag :  WOCHE[sprache]){
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
