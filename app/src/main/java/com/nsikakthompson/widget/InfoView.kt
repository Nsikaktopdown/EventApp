package com.nsikakthompson.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import com.nsikakthompson.R
import com.nsikakthompson.databinding.InfoViewBinding
import android.view.LayoutInflater
import androidx.databinding.BindingMethod
import java.lang.reflect.Array.set





class InfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    lateinit var constraintLayout: ConstraintLayout
    lateinit var attributes: TypedArray
    lateinit var textViewTitle: TextView
    lateinit var textViewDesc: TextView
    private var binding: InfoViewBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.info_view, this, true)

        textViewTitle =  binding.tvHeading
        textViewDesc =  binding.tvDesc
        constraintLayout = findViewById<ConstraintLayout>(R.id.info_view_body)
        attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoView)
        binding.tvHeading.text = attributes.getString(R.styleable.InfoView_title)
        binding.tvDesc.text = attributes.getString(R.styleable.InfoView_description)
        binding.ivImage.setImageResource(
            attributes.getResourceId(
                R.styleable.InfoView_icon,
                R.drawable.ic_person
            )
        )

        attributes.recycle()

    }

    override fun setBackgroundDrawable(drawable: Drawable) {
        constraintLayout.setBackgroundDrawable(drawable)
        // attributes.recycle()
    }

}
