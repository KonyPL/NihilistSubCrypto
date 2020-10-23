package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AboutController {

    @FXML private Button closeButton;
    @FXML private Label text;
    @FXML private Label monoText;

    public AboutController() {}

    public void setLang( boolean isPolish) {
        if(isPolish)
        {
            text.setText("Program \"Nihilist Substitution Cypher\" to program pozwalający na szyfrowanie i deszyfrowanie tekstu metodą podstawieniową nihilistów.\n" +
                    "Do szyfrowania i deszyfrowania program wymaga dwóch kluczy:\n" +
                    "1. Pierwszy używany jest do stworzenia szachownicy Polibiusza (program automatycznie przekształca litery na wielkie, usuwa powtórzenia,\n" +
                    "\tzamienia \"j\" -> \"i\", usuwa nieobsługiwane znaki i zamienia polskie litery na odpowiedniki bez ogonków)\n" +
                    "2. Drugi używany do utworzenia tekstu wynikowego - im dłuższy klucz, tym szyfr trudniejszy do złamania.\n" +
                    "\n" +
                    "Dzięki szachownicy polibiusza program przypisuje każdej literze dwucyfrowy numer, na który składa się wiersz i kolumna [W*10+K].\n" +
                    "Klucz drugi jest \"tłumaczony\" na ciąg cyfr i tworzy pierwszy wiersz tabeli wynikowej. Kolejne wiersze tabeli wypełniane są szyfrowanym \n" +
                    "tekstem, \"tłumaczone\" w taki sam sposób jak klucz 2, z tą różnicą, że każda wartość reprezentująca literę w danej kolumnie \n" +
                    "jest powiększana o wartość tej kolumny z pierwszego wiersza.\n");
            monoText.setText("Przykład:\n" +
                    "klucz 1: SZYFRY\n" +
                    "klucz 2: PODY\n" +
                    "tekst: PODSTAWYOCHRONY\n" +
                    "\n" +
                    "Szachownica Polibiusza:\n" +
                    "[\\|1|2|3|4|5]\n" +
                    "[1[S|Z|Y|F|R]\n" +
                    "[2[A|B|C|D|E]\n" +
                    "[3[G|H|I|K|L]\n" +
                    "[4[M|N|O|P|Q]\n" +
                    "[5[T|U|V|W|X]\n" +
                    "\n" +
                    "Klucz 2:\n" +
                    "[ P| O| D| Y]\n" +
                    "[44|43|24|13]\n" +
                    "\n" +
                    "Tekst wynikowy: zaszyfrowany szachownicą + powiększony o Klucz 2\n" +
                    "[88|86|48|24]\n" +
                    "[95|64|78|26]\n" +
                    "[87|66|56|28]  ->  888648249564782687665628878537\n" +
                    "[87|85|37]");
        } else {
            text.setText("\"Nihilist Substitution Cypher\" is a program that allows you to encrypt and decrypt text using the nihilist substitution method.\n" +
                    "The program requires two keys for encryption and decryption:\n" +
                    " 1. The first one is used to create a Polybius square (the program automatically converts letters to uppercase, removes repetitions,\n" +
                    "\treplaces \"j\" -> \"i\", removes unsupported characters and replaces Polish letters with equivalents without tails)\n" +
                    "2.The second one is used to create the output text - the longer the key, the more difficult is cipher to break.\n" +
                    "\n" +
                    "Thanks to the polybius square, the program assigns a two-digit number to each letter, consisting of a row and a column [R*10+C].\n" +
                    "The second key is \"translated\" into a string of numbers and creates the first row of the result table. Subsequent rows of the table\n" +
                    "are filled with encrypted text, translated the same way as key 2, except that each value representing a letter in a given column\n" +
                    "is increased by the value of that column from the first row.");
            monoText.setText("Example:\n" +
                    "key 1: SZYFRY\n" +
                    "key 2: PODY\n" +
                    "text: PODSTAWYOCHRONY\n" +
                    "\n" +
                    "Polybius Square:\n" +
                    "[\\|1|2|3|4|5]\n" +
                    "[1[S|Z|Y|F|R]\n" +
                    "[2[A|B|C|D|E]\n" +
                    "[3[G|H|I|K|L]\n" +
                    "[4[M|N|O|P|Q]\n" +
                    "[5[T|U|V|W|X]\n" +
                    "\n" +
                    "Key 2:\n" +
                    "[ P| O| D| Y]\n" +
                    "[44|43|24|13]\n" +
                    "\n" +
                    "Output text: encrypted with the square + increased by the second key\n" +
                    "[88|86|48|24]\n" +
                    "[95|64|78|26]\n" +
                    "[87|66|56|28]  ->  888648249564782687665628878537\n" +
                    "[87|85|37]");
        }
    }

    @FXML void initialize() {

    }

    @FXML private void closeWindow(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
