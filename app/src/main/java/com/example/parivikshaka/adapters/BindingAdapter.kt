package com.example.parivikshaka.adapters
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.parivikshaka.models.Sampledata



@BindingAdapter("items")
fun setItems(recyclerView: RecyclerView,liveData: LiveData<List<Sampledata>>?){
    liveData?.observe(recyclerView.context as LifecycleOwner){
        items->
        (recyclerView.adapter as? SampleAdapter)?.apply {
//            setItems(items)
        }
    }
}