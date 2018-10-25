package pt.ulp.ei.calculoiuc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;
import android.view.View;

public class AtividadeCalculoIUC extends AppCompatActivity {
    double imposto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividade_calculo_iuc);

        TextView show_result= findViewById(R.id.show_result);
        Intent intent = getIntent();

        String str_cilindrada = intent.getStringExtra("cilindrada");
        String str_co = intent.getStringExtra("co");

        imposto=0.0;
        switch(str_cilindrada){
            case "até 1.250":
                imposto+=33.10;
                break;
            case "mais de 1.250 até 1.750":
                imposto+=66.50;
                break;
            case "mais de 1.750 até 2.500":
                imposto+=133.01;
                break;
            case "mais de 2.500":
                imposto+=455.30;
                break;
        }
        switch(str_co){
            case "até 120":
                imposto+=57.0;
                break;
            case "mais de 120 até 180":
                imposto+=86.0;
                break;
            case "mais de 180 até 250":
                imposto+=187.0;
                break;
            case "mais de 250":
                imposto+=321.0;
                break;

        }

        show_result.setText(imposto+"€");
    }

    public void Fechar_Click(View view ) {

        this.finish();
    }
    @Override
    public void finish(){
        Intent data = new Intent();
        data.putExtra("imposto", String.valueOf(imposto));
        setResult(RESULT_OK, data);
        super.finish();
    }


}

