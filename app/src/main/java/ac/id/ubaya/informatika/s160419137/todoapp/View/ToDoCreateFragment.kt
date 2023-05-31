package ac.id.ubaya.informatika.s160419137.todoapp.View

import ac.id.ubaya.informatika.s160419137.todoapp.Model.Todo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ac.id.ubaya.informatika.s160419137.todoapp.R
import ac.id.ubaya.informatika.s160419137.todoapp.ViewModel.ToDoDetailViewModel
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.android.material.textfield.TextInputEditText

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ToDoCreateFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ToDoCreateFragment : Fragment() {
    private lateinit var viewModel:ToDoDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_to_do_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ToDoDetailViewModel::class.java)
        val btnCreate = view.findViewById<Button>(R.id.btnCreate)
        btnCreate.setOnClickListener {
            val txtTitle = view.findViewById<TextInputEditText>(R.id.txtTitle)
            val txtNotes = view.findViewById<TextInputEditText>(R.id.txtNote)
            var radioGroup = view.findViewById<RadioGroup>(R.id.radioGroupPriority)
            var radioSelected = view.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)


            var todo = Todo(txtTitle.text.toString(), txtNotes.text.toString(),radioSelected.tag.toString().toInt())

            viewModel.addTodo(todo)

            Toast.makeText(view.context, "Data added", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }
    }

}