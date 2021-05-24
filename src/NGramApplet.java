import java.awt.*;
import java.applet.*;
/*<applet code=NGramApplet archive=NGram.jar width=600 height=400>
</applet>*/
public class NGramApplet extends Applet {
  Panel p1 = new Panel();
  Panel p2 = new Panel();
  Panel p3 = new Panel();
  TextArea t1 = 
     new TextArea("introduction to modern information retrieval", 2, 30);
  TextArea t2 = 
     new TextArea("information retrieval applications", 2, 30);
  TextArea t3 = new TextArea("Result: Similarity of two strings:", 20, 30);
  Button b = new Button("Compare");
  Choice choice = new Choice();

  public void init() {
    setLayout(new BorderLayout());
    p1.setLayout(new GridLayout(1,2));
    p1.add(t1);
    p1.add(t2);
    p2.setLayout(new GridLayout(1,1));
    p2.add(t3);
    p3.setLayout(new FlowLayout());
    p3.add(b);
     choice.addItem("2-gram");
     choice.addItem("3-gram");
     choice.addItem("4-gram");
     choice.addItem("5-gram");
     choice.addItem("6-gram");
     choice.addItem("7-gram");
     choice.addItem("8-gram");
     choice.addItem("9-gram");
    p3.add(choice);
    setLayout(new GridLayout(3, 2));
    add("East", p1);
    add("West", p2);
    add("Center", p3);
  }
  public boolean action (Event evt, Object arg) {
    if (evt.target.equals(b)) {
      getAppletContext().showStatus("Compare");
      String str1 = t1.getText();
      String str2 = t2.getText();
      int ch = choice.getSelectedIndex();
      NGram ngram = new NGram();
      String result = ngram.compare(str1, str2, ch+2);
      t3.setText(result);
    }
    else
      return super.action(evt, arg);
    return true;
  }
}
