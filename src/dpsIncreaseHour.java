import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.Label;

import java.text.DecimalFormat;

@SuppressWarnings("Convert2Lambda")
public class dpsIncreaseHour extends Application {
    private final Label dpsIncHOut;
    private final Label dpsMultiLvlOut;
    private final Label dpsMultiCardsOut;
    private final DecimalFormat df;
    private int dpsLvl;
    private int dpsGoal;
    private int cardsGoal;
    private int timeLvlM;
    private int timeLvlS;
    private int cardsH;

    {
        df = new DecimalFormat ("0.000000");
        dpsMultiCardsOut = new Label ("NaN");
        dpsMultiLvlOut = new Label ("NaN");
        dpsIncHOut = new Label ("NaN");
        cardsH = 0;
        timeLvlM = 0;
        timeLvlS = 0;
        dpsLvl = 0;
        dpsGoal = 0;
        cardsGoal = 0;
    }

    public static void main(String[] args) {
        launch (args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox root = new HBox ();
        VBox left = new VBox ();
        VBox right = new VBox ();
        HBox time = new HBox ();
        primaryStage.setTitle ("IOU Inf Tier dps increase calculator");
        primaryStage.setScene (new Scene (root, 450, 300));

        Label dpsLvlLabel = new Label ("Increased dps with lvl increase:");
        Label dpsGoalLabel = new Label ("Increased dps with one reached Card Goal:");
        Label cardsGoalLabel = new Label ("Cards needed to reach goal (incl. overspent):");
        Label timeLvlLabel = new Label ("Time to get a lvl:");
        Label timeLvlMLabel = new Label ("m:");
        Label timeLvlSLabel = new Label ("s:");
        Label cardsHLabel = new Label ("Cards gain per hour:");

        Label dpsIncLabel = new Label ("dps increase per Hour:");
        Label dpsMultiLvlLabel = new Label ("Multiplier for lvl-dps:");
        Label dpsMultiCardsLabel = new Label ("Multiplier for cards-dps:");

        TextField dpsLvlIn = new TextField ("");
        TextField dpsGoalIn = new TextField ("");
        TextField cardsGoalIn = new TextField ("");
        TextField timeLvlMIn = new TextField ("");
        TextField timeLvlSIn = new TextField ("");
        TextField cardsHIn = new TextField ("");

        dpsLvlLabel.setMinHeight (26);
        dpsGoalLabel.setMinHeight (26);
        cardsGoalLabel.setMinHeight (26);
        timeLvlLabel.setMinHeight (26);
        cardsHLabel.setMinHeight (26);
        dpsIncLabel.setMinHeight (26);
        dpsMultiLvlLabel.setMinHeight (26);
        dpsMultiCardsLabel.setMinHeight (26);

        dpsLvlIn.setAlignment (Pos.CENTER_RIGHT);
        dpsLvlIn.setMaxWidth (110);
        dpsLvlIn.setMinHeight (26);
        dpsGoalIn.setAlignment (Pos.CENTER_RIGHT);
        dpsGoalIn.setMaxWidth (110);
        dpsGoalIn.setMinHeight (26);
        cardsGoalIn.setAlignment (Pos.CENTER_RIGHT);
        cardsGoalIn.setMaxWidth (110);
        cardsGoalIn.setMinHeight (26);
        cardsHIn.setAlignment (Pos.CENTER_RIGHT);
        cardsHIn.setMaxWidth (110);
        cardsHIn.setMinHeight (26);
        timeLvlMIn.setAlignment (Pos.CENTER_RIGHT);
        timeLvlMIn.setMaxWidth (45);
        timeLvlMIn.setMinHeight (26);
        timeLvlSIn.setAlignment (Pos.CENTER_RIGHT);
        timeLvlSIn.setMaxWidth (35);
        timeLvlSIn.setMinHeight (26);
        dpsIncHOut.setMinHeight (26);
        dpsMultiLvlOut.setMinHeight (26);
        dpsMultiCardsOut.setMinHeight (26);

        right.setAlignment (Pos.TOP_RIGHT);
        time.setAlignment (Pos.CENTER);
        time.getChildren ().addAll (timeLvlMLabel, timeLvlMIn, timeLvlSLabel, timeLvlSIn);
        left.getChildren ().addAll (dpsLvlLabel, dpsGoalLabel, cardsGoalLabel, timeLvlLabel, cardsHLabel, dpsIncLabel, dpsMultiLvlLabel, dpsMultiCardsLabel);
        right.getChildren ().addAll (dpsLvlIn, dpsGoalIn, cardsGoalIn, time, cardsHIn, dpsIncHOut, dpsMultiLvlOut, dpsMultiCardsOut);
        root.getChildren ().addAll (left, right);

        dpsLvlIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    dpsLvlIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    dpsLvl = Integer.valueOf (dpsLvlIn.getText ());
                } catch (NumberFormatException e) {
                    dpsLvl = 0;
                }
                output ();
            }
        });
        dpsGoalIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    dpsGoalIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    dpsGoal = Integer.valueOf (dpsGoalIn.getText ());
                } catch (NumberFormatException e) {
                    dpsGoal = 0;
                }
                output ();
            }
        });
        cardsGoalIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    cardsGoalIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    cardsGoal = Integer.valueOf (cardsGoalIn.getText ());
                } catch (NumberFormatException e) {
                    cardsGoal = 0;
                }
                output ();
            }
        });
        timeLvlMIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    timeLvlMIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    timeLvlM = Integer.valueOf (timeLvlMIn.getText ());
                } catch (NumberFormatException e) {
                    timeLvlM = 0;
                }
                output ();
            }
        });
        timeLvlSIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    timeLvlSIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    timeLvlS = Integer.valueOf (timeLvlSIn.getText ());
                } catch (NumberFormatException e) {
                    timeLvlS = 0;
                }
                output ();
            }
        });
        cardsHIn.textProperty ().addListener (new ChangeListener<String> () {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
                if (!newValue.matches ("\\d*")) {
                    cardsHIn.setText (newValue.replaceAll ("[^\\d]", ""));
                }
                try {
                    cardsH = Integer.valueOf (cardsHIn.getText ());
                } catch (NumberFormatException e) {
                    cardsH = 0;
                }
                output ();
            }
        });
        primaryStage.show ();

    }

    private void output() {
        if (cardsH == 0 || timeLvlM == 0 || dpsLvl == 0 || dpsGoal == 0 || cardsGoal == 0) {
            dpsIncHOut.setText ("NaN");
            dpsMultiLvlOut.setText ("NaN");
            dpsMultiCardsOut.setText ("NaN");

        } else {
            double out[] = calc (dpsLvl, dpsGoal, cardsGoal, timeLvlM, timeLvlS, cardsH);
            dpsIncHOut.setText (String.valueOf ((int) out[2]));
            dpsMultiLvlOut.setText (String.valueOf (df.format (out[0])));
            dpsMultiCardsOut.setText (String.valueOf (df.format (out[1])));
        }

    }

    private double[] calc(int dpsLvl, int dpsGoal, int cardsGoal, int timeLvlM, int timeLvlS, int cardsHour) {
        double[] out = new double[3];
        int timeLvl = timeLvlM * 60 + timeLvlS;
        out[0] = 3600.0 / timeLvl;
        out[1] = (double) cardsHour / cardsGoal;
        out[2] = dpsLvl * out[0] + dpsGoal * out[1];
        return out;
    }
}
