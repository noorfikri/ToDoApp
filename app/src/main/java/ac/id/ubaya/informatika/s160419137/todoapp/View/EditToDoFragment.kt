package ac.id.ubaya.informatika.s160419137.todoapp.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ac.id.ubaya.informatika.s160419137.todoapp.R
import ac.id.ubaya.informatika.s160419137.todoapp.ViewModel.ToDoDetailViewModel
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditToDoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditToDoFragment : Fragment() {
    private lateinit var viewModel: ToDoDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var txtHeader = view?.findViewById<TextView>(R.id.textToDoHeader)
        var btnEdit = view?.findViewById<Button>(R.id.btnCreate)

        txtHeader?.text = "Edit ToDo"
        btnEdit?.text = "Save Changes"
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_create, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var btnEdit = view.findViewById<Button>(R.id.btnCreate)
        var txtTitle = view.findViewById<TextInputEditText>(R.id.txtTitle)
        var txtNotes = view.findViewById<TextInputEditText>(R.id.txtNote)
        var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
        var radioSelected = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)


        btnEdit.setOnClickListener {

            viewModel.update(txtTitle.text.toString(), txtNotes.text.toString(), radioSelected.tag.toString().toInt(), id)
            Toast.makeText(view.context, "Todo updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()

        }

        viewModel = ViewModelProvider(this).get(ToDoDetailViewModel::class.java)

        val uuid = EditToDoFragmentArgs.fromBundle(requireArguments()).id
        viewModel.fetch(uuid)
        observeViewModel(view)
    }

    fun observeViewModel(view: View) {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            var txtTitle = view.findViewById<TextInputEditText>(R.id.txtTitle)
            var txtNotes = view.findViewById<TextInputEditText>(R.id.txtNote)
            var radioHigh = view.findViewById<RadioButton>(R.id.radioHigh)
            var radioMed = view.findViewById<RadioButton>(R.id.radioMid)
            var radioLow = view.findViewById<RadioButton>(R.id.radioLow)

            when(it.priority){
                1 -> radioLow.isChecked = true
                2 -> radioMed.isChecked = true
                else -> radioHigh.isChecked = true
            }

            txtTitle.setText(it.title)
            txtNotes.setText(it.notes)
        })
    }

}