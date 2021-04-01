package com.example.homework4

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.res.use
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.example.homework4.databinding.ListTileBinding

class ListTileView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attributeSet, defStyleAttr, defStyleRes) {

    private val heightOneLine = context.dpToPx(56)
    private val heightTwoLines = context.dpToPx(72)
    private val titleBaselineOffset = context.dpToPx(32)
    private val binding: ListTileBinding = ListTileBinding.inflate(
        LayoutInflater.from(context),
        this,
        true
    )

    var leading: Drawable?
        get() = binding.leading.drawable
        set(value) {
            binding.leading.apply {
                isVisible = value != null
                setImageDrawable(value)
            }
        }
    var title by binding.title::text
    var subtitle: CharSequence?
        get() = binding.subtitle.text
        set(value) {
            val hasSubtitle = value != null
            binding.root.updateLayoutParams {
                height = if (hasSubtitle) heightTwoLines else heightOneLine
            }
            binding.title.apply {
                updateLayoutParams<ConstraintLayout.LayoutParams> {
                    verticalBias = if (hasSubtitle) 0f else 0.5f
                }
            }
            binding.subtitle.apply {
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

fun Context.dpToPx(dp: Number) = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    dp.toFloat(),
    resources.displayMetrics
).toInt()
