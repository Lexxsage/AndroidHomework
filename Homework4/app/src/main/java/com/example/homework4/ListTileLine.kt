package com.example.homework4

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.example.homework4.databinding.ListTileLineBinding

class ListTileLine @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val heightOneLine = context.dpToPx(56)
    private val heightTwoLines = context.dpToPx(72)
    private val binding: ListTileLineBinding = ListTileLineBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var leading: Drawable?
        get() = binding.leadingline.drawable
        set(value) {
            binding.leadingline.apply {
                isVisible = value != null
                setImageDrawable(value)
            }
        }
    var title by binding.titleline::text
    var subtitle: CharSequence?
        get() = binding.subtitleline.text
        set(value) {
            val hasSubtitle = value != null
            binding.root.updateLayoutParams {
                height = if (hasSubtitle) heightTwoLines else heightOneLine
            }
            binding.titleline.apply {
                updateLayoutParams<ConstraintLayout.LayoutParams> {
                    verticalBias = if (hasSubtitle) 0f else 0.5f
                }
            }
            binding.subtitleline.apply {
                text = value
                isVisible = hasSubtitle
            }
        }

    init {
        context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.ListTileView,
            defStyleAttr,
            defStyleRes
        ).use {
            leading = it.getDrawable(R.styleable.ListTileView_leading)
            title = it.getString(R.styleable.ListTileView_title)
            subtitle = it.getString(R.styleable.ListTileView_subtitle)
        }
    }

}