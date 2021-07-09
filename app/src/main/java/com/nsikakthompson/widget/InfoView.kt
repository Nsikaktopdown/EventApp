package com.nsikakthompson.widget

import android.content.Context
import android.content.res.TypedArray
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.nsikakthompson.R

class InfoView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    lateinit var constraintLayout: ConstraintLayout
    lateinit var attributes: TypedArray
    lateinit var textViewTitle: TextView
    lateinit var textViewDesc: TextView

    init {
        inflate(context, R.layout.info_view, this)

        textViewTitle = findViewById(R.id.tvHeading)
        textViewDesc = findViewById(R.id.tvDesc)
        val imageView = findViewById<ImageView>(R.id.ivImage)
        constraintLayout = findViewById<ConstraintLayout>(R.id.info_view_body)
        attributes = context.obtainStyledAttributes(attrs, R.styleable.InfoView)
        textViewTitle.text = attributes.getString(R.styleable.InfoView_title)
        textViewDesc.text = attributes.getString(R.styleable.InfoView_description)
        imageView.setImageResource(attributes.getResourceId(R.styleable.InfoView_icon, R.drawable.ic_person ))
        attributes.recycle()

    }

    override fun setBackgroundDrawable(drawable: Drawable) {
        constraintLayout.setBackgroundDrawable(drawable)
        // attributes.recycle()
    }

}
