package com.adsamcik.cardlisttest;

import android.os.Bundle;
import android.widget.Toast;

import com.adsamcik.recycler.AppendPriority;
import com.adsamcik.recycler.SortableAdapter;
import com.adsamcik.recycler.card.CardListAdapter;
import com.adsamcik.recycler.card.table.TableCard;
import com.adsamcik.recycler.card.table.TableCardCreator;

import java.util.ArrayList;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//buttons do not have background without this
		//getApplicationContext().setTheme(R.style.AppThemeDark);
		//setTheme(R.style.AppThemeDark);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		RecyclerView recyclerView = findViewById(R.id.recycler_view);
		TableCardCreator creator = new TableCardCreator(R.style.AppThemeDark);
		CardListAdapter<TableCard.ViewHolder, TableCard> adapter = CardListAdapter.Companion.addTo(recyclerView, creator);

		TableCard first = new TableCard(false, 2);
		first.addData("hello", "world");
		first.addData("hi", "world");
		first.setTitle("Append any");

		TableCard second = new TableCard(true, 2);
		second.addData("numbered", "world");
		second.addButton("button", view -> Toast.makeText(this, "Clicked a button", Toast.LENGTH_SHORT).show());
		second.setTitle("Append first");


		TableCard third = new TableCard(false, 2);
		for (int i = 0; i < 10; i++)
			third.addData("data " + i, Integer.toString(i));
		third.setTitle("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum euismod ipsum vel fermentum vulputate. Nulla ultrices quam ut dolor bibendum semper. Quisque placerat cursus ipsum, sit amet rutrum diam porta sed. Aenean arcu est, scelerisque non neque sed, vulputate lacinia risus. In aliquet egestas ullamcorper. Phasellus vitae nunc aliquet, tincidunt metus ut, maximus magna. Nam fringilla porta enim euismod sagittis. Praesent placerat lacinia mauris id tempor. Nullam vulputate, nibh in tincidunt tempus, mauris libero sagittis arcu, a mollis libero tortor non ante. ");


		adapter.add(new SortableAdapter.SortableData<>(third, AppendPriority.Companion.getAny()));
		adapter.add(new SortableAdapter.SortableData<>(first, AppendPriority.Companion.getAny()));
		Random random = new Random();

		ArrayList<SortableAdapter.SortableData<TableCard>> tableCards = new ArrayList<>(10);
		for (int i = 0; i < 10; i++) {
			TableCard tb = new TableCard(false, 2);
			int rn = 3 + random.nextInt(9);
			for (int y = 0; y < rn; y++) {
				if (y % 2 == 0)
					tb.addData("0", "w");
				else
					tb.addData("hi", "world");
			}
			tableCards.add(new SortableAdapter.SortableData<>(tb, AppendPriority.Companion.getAny()));
		}

		adapter.addAll(tableCards);

		adapter.add(new SortableAdapter.SortableData<>(second, AppendPriority.Companion.getStart()));

		LinearLayoutManager manager = new LinearLayoutManager(this);
		manager.setOrientation(RecyclerView.VERTICAL);
		recyclerView.setLayoutManager(manager);
	}
}
