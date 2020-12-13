package com.example.madlevel5task2

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_game.*

class AddGameFragment : Fragment() {

    private val viewModel: GameViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_add_game, menu)

        val actionBar = (activity as AppCompatActivity?)!!.supportActionBar

        // Disable the actionBar back button
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true) // enable the button
            actionBar.setDisplayHomeAsUpEnabled(true) // enable the left caret
            actionBar.setDisplayShowHomeEnabled(true) // enable the icon
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        saveClick()

        return inflater.inflate(R.layout.fragment_add_game, container, false)
    }

    private fun saveClick() {
        val mainActivity = activity as MainActivity?

        mainActivity!!.fabSave.setOnClickListener {
            val title = title.text.toString()
            val platform = platform.text.toString()

            val day = day.text.toString()
            val month = month.text.toString()
            val year = year.text.toString()

            if (title.isNotEmpty()
                && platform.isNotEmpty()
                && day.isNotEmpty()
                && month.isNotEmpty()
                && year.isNotEmpty()) {

                viewModel.addGame(title, platform, day, month, year)

                // go back to the home screen
                findNavController().popBackStack()
            }

            else {
                Toast.makeText(context, getString(R.string.badInput), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}