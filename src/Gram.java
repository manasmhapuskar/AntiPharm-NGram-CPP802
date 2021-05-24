
import java.lang.*;

class Gram {
String gram;
int occurences;
String str;

public Gram (String inGram, String inString) {
  gram = inGram;
  str = inString;
  occurences = 0;
  int ng = gram.length();
  int len = str.length();
  for (int i = 0; i <= (len - ng); i++) {
    String gr = str.substring(i, i + ng);
    if (gram.equalsIgnoreCase(gr)) {occurences++;}
  }
}

}
