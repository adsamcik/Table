package com.adsamcik.cardlist.card.table

import android.util.Pair
import android.view.View
import android.widget.TableLayout
import androidx.recyclerview.widget.RecyclerView
import com.adsamcik.cardlist.AppendPriority
import com.adsamcik.cardlist.card.Card

/**
 * Implementation of [Card] that allows displaying of simple tables inside a card.
 */
class TableCard
/**
 * TableCard constructor
 *
 * @param rowCount   number of data (used to initialize array holding data)
 * @param showRowNumber show number of row (starts at 1)
 */
(val showRowNumber: Boolean,
 override val appendPriority: AppendPriority = AppendPriority.Any,
 rowCount: Int = 4) : Card {

	data class ViewHolder(val cardView: View, val layout: TableLayout) : RecyclerView.ViewHolder(cardView)

	/**
	 * Table title
	 */
	var title: String? = null

	private val mutableData: MutableList<Pair<String, String>> = ArrayList(rowCount)
	val data: List<Pair<String, String>> get() = mutableData

	private val mutableButtons: MutableList<Pair<String, View.OnClickListener>> = ArrayList(0)
	val buttons: List<Pair<String, View.OnClickListener>> get() = mutableButtons


	/**
	 * Add button to the bottom of the table
	 *
	 * @param text     title of the button
	 * @param callback on click callback
	 * @return this table
	 */
	fun addButton(text: String, callback: View.OnClickListener): TableCard {
		mutableButtons.add(Pair(text, callback))
		return this
	}

	/**
	 * Adds data to 2 columns on the last row, only use this with 2 columns (+1 if row numbering is enabled)
	 *
	 * @param name  row name
	 * @param value row value
	 * @return this table
	 */
	fun addData(name: String, value: String): TableCard {
		mutableData.add(Pair(name, value))
		return this
	}
}
