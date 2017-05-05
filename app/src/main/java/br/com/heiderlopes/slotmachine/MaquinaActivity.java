package br.com.heiderlopes.slotmachine;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

import info.hoang8f.widget.FButton;

public class MaquinaActivity extends AppCompatActivity {

    private ImageView ivSlot1, ivSlot2, ivSlot3;
    private Roda slot1, slot2, slot3;
    private FButton btJogar;

    public static final Random RANDOM = new Random();

    public static long randomLong(long lower, long upper) {
        return lower + (long) (RANDOM.nextDouble() * (upper - lower));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maquina);
        ivSlot1 = (ImageView) findViewById(R.id.ivSlot1);
        ivSlot2 = (ImageView) findViewById(R.id.ivSlot2);
        ivSlot3 = (ImageView) findViewById(R.id.ivSlot3);

        btJogar = (FButton) findViewById(R.id.btJogar);
    }

    public void jogar(View v) {

        rodarSlot1();
        rodarSlot2();
        rodarSlot3();

        btJogar.setEnabled(false);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                slot1.pararDeRodar();
                slot2.pararDeRodar();
                slot3.pararDeRodar();

                exibeResultado();

                btJogar.setEnabled(true);
            }
        }, 3000);
    }


    private void exibeResultado() {
        if (slot1.indiceAtual == slot2.indiceAtual && slot2.indiceAtual == slot3.indiceAtual) {
            Toast.makeText(this, "Você ganhou", Toast.LENGTH_SHORT).show();
        } else if (slot1.indiceAtual == slot2.indiceAtual || slot2.indiceAtual == slot3.indiceAtual
                || slot1.indiceAtual == slot3.indiceAtual) {
            Toast.makeText(this, "Pequena Premiação", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Você perdeu", Toast.LENGTH_SHORT).show();
        }

        //btn.setText("Start");
    }

    private void rodarSlot1() {
        slot1 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot1.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(0, 200));
        slot1.start();
    }

    private void rodarSlot2() {
        slot2 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot2.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(150, 400));
        slot2.start();
    }

    private void rodarSlot3() {
        slot3 = new Roda(new Roda.RodaListener() {
            @Override
            public void novaImagem(final int resourceID) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivSlot3.setImageResource(resourceID);
                    }
                });
            }
        }, 200, randomLong(300, 600));
        slot3.start();
    }
}