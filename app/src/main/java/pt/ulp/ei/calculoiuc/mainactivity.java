package pt.ulp.ei.calculoiuc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.AlertDialog;
import android.util.Log;
import android.content.Intent;
import android.widget.Toast;


public class mainactivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);

    }

    public void CalcularIUC (View v){

        RadioGroup cilindrada = findViewById(R.id.rgroup_cilindrada);
        RadioGroup CO = findViewById(R.id.rgroup_co);
        TextView mostrar= findViewById(R.id.show_calculo);

        double imposto=0.0;

        int nr_cilindrada = cilindrada.getCheckedRadioButtonId();
        int nr_co = CO.getCheckedRadioButtonId();
        Intent  intent = new Intent(this, AtividadeCalculoIUC.class);



        if(nr_cilindrada == -1 || nr_co == -1) {
            Log.d("DEBUGGING", "Entrei");
            String mensagem = "";
            AlertDialog.Builder builder = new AlertDialog.Builder(mainactivity.this);


            if ((nr_cilindrada == -1) && (nr_co == -1)) {

                mensagem = "Esqueceu de preencher a cilindrade e o CO";


            } else {
                if (nr_cilindrada == -1) {
                    mensagem = "Esqueceu de preencher a cilindrade";
                } else {

                    mensagem = "Esqueceu de preencher o CO";

                }
            }

            builder.setMessage(mensagem)
                    .setTitle("Erro");
            AlertDialog dialog = builder.create();
            dialog.show();

        }else{
            switch(nr_cilindrada){
                case R.id.cilindrada_ate_1250:
                    intent.putExtra("cilindrada","até 1.250");
                    break;
                case R.id.cilindrada_1250_até_1750:
                    intent.putExtra("cilindrada","mais de 1.250 até 1.750");
                    break;
                case R.id.cilindrada_1750_ate_2:
                    intent.putExtra("cilindrada","mais de 1.750 até 2.500");
                    break;
                case R.id.cilindrada_mais_2500:
                    intent.putExtra("cilindrada","mais de 2.500");
                    break;
            }

            switch(nr_co){
                case R.id.CO_ate_120:
                    intent.putExtra("co","até 120");
                    break;
                case R.id.CO_120_ate_180:
                    intent.putExtra("co","mais de 120 até 180");
                    break;
                case R.id.CO_180_ate_250:
                    intent.putExtra("co","mais de 180 até 250");
                    break;
                case R.id.CO_mais_250:
                    intent.putExtra("co","mais de 250");
                    break;

            }


            startActivityForResult(intent, REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (data.hasExtra("imposto")) {
                String imposto = data.getExtras().getString("imposto");
                Toast.makeText(this, "IUC a pagar " + imposto, Toast.LENGTH_LONG).show();
                TextView show_calculo = findViewById(R.id.show_calculo);
                show_calculo.setText("Imposto :" + imposto);
            }

        }
    }

}