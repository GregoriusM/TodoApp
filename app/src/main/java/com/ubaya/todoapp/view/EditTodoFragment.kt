package com.ubaya.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ubaya.todoapp.R
import com.ubaya.todoapp.databinding.FragmentCreateTodoBinding
import com.ubaya.todoapp.model.Todo
import com.ubaya.todoapp.viewmodel.DetailTodoViewModel

class EditTodoFragment : Fragment() {
    private lateinit var binding: FragmentCreateTodoBinding
    private lateinit var viewModel: DetailTodoViewModel
    private lateinit var todo: Todo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateTodoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get((DetailTodoViewModel::class.java))

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)

        observeViewModel()

        binding.txtJudulTodo.text = "Edit Todo"
        binding.btnAdd.text = "Save Changes"

        binding.btnAdd.setOnClickListener {
            var radioId = binding.radioGroupPriority.checkedRadioButtonId
            var checkedRadio = view.findViewById<RadioButton>(radioId)

            todo.title = binding.txtTitle.text.toString()
            todo.notes = binding.txtNotes.text.toString()
            todo.priority = checkedRadio.tag.toString().toInt()

            viewModel.update(todo)
            Toast.makeText(view.context, "Todo updated",
                Toast.LENGTH_SHORT).show()

        }

    }

    fun observeViewModel(){
        viewModel.todoLD.observe(viewLifecycleOwner, Observer{
            todo = it
            binding.txtTitle.setText(it.title)
            binding.txtNotes.setText(it.notes)

            when (it.priority){
                1 -> binding.radioLow.isChecked = true
                2 -> binding.radioMedium.isChecked = true
                else -> binding.radioHigh.isChecked = true
            }
        })
    }

}