package mumayank.dialogeeproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import mumayank.dialogee.Dialogee;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialogee dialogee = new Dialogee.Builder(MainActivity.this, "Display message?")
                        .setDescription("This is some description")
                        .setIcon(R.drawable.ic_action_name)
                        .setCancellable(true)
                        .setPositiveButtonText("Message 1")
                        .setNegativeButtonText("Message 2")
                        .setNeutralButtonText("Message 3")
                        .setPositiveButtonAction(new Dialogee.PositiveButton() {
                            @Override
                            public void action() {
                                Toast.makeText(MainActivity.this, "positive button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButtonAction(new Dialogee.NegativeButton() {
                            @Override
                            public void action() {
                                Toast.makeText(MainActivity.this, "negative button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNeutralButtonAction(new Dialogee.NeutralButton() {
                            @Override
                            public void action() {
                                Toast.makeText(MainActivity.this, "neutral button", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();

                dialogee.show();

            }
        });

    }

}
