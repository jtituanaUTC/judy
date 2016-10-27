package com.example.ejemplo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	
	EditText segundos;
	ListView rango, resultado;
	Chronometer cronometro;
	int i=0;
	String tiempoActual;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		segundos = (EditText)findViewById(R.id.txt_segundos);
		rango = (ListView) findViewById(R.id.lst_numeros_desendentes);
		resultado = (ListView) findViewById(R.id.lst_resultado);
		cronometro = (Chronometer) findViewById(R.id.crn_tiempo);
		
		ArrayList<String> numeros = new ArrayList<String>();
		final ArrayList<String> multiplos = new ArrayList<String>();
		
		numeros.add("10");
		numeros.add("9");
		numeros.add("8");
		numeros.add("7");
		numeros.add("6");
		numeros.add("5");
		numeros.add("4");
		numeros.add("3");
		numeros.add("2");
		numeros.add("1");
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,numeros);
		rango.setAdapter(adapter);
		
		final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,multiplos);
		resultado.setAdapter(adapter2);
		
		rango.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				multiplos.clear();
				int c= Integer.parseInt(segundos.getText().toString());
				int contador =0;
				String m = (String) parent.getItemAtPosition(position);
				int m2 = Integer.parseInt(m);
				for (int i = 1; i <= c; i++) {
		        		contador = contador + m2;
		        		multiplos.add(Integer.toString(contador));
		        		adapter2.notifyDataSetChanged();
				}
				
				cronometro.setBase(SystemClock.elapsedRealtime());
				cronometro.start();
				
				cronometro.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
					
					@Override
					public void onChronometerTick(Chronometer chronometer) {
						
						if( i==3){
							cronometro.stop();
							adapter2.clear();
							i=0;
						}
						i++;
						
					}
				});
				i=0;
			}
			
			
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
