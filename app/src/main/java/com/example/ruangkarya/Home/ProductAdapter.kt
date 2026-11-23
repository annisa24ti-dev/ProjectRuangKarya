package com.example.ruangkarya.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ruangkarya.databinding.ItemProductBinding

class ProductAdapter(
    private var productList: List<ProductModel>,
    private val onProductClick: (ProductModel) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(
        val binding: ItemProductBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductViewHolder {

        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductViewHolder,
        position: Int
    ) {
        val product = productList[position]
        val binding = holder.binding

        binding.tvProductName.text = product.name
        binding.tvProductCategory.text = product.category
        binding.tvProductPrice.text = product.price
        binding.tvProductEmoji.text = product.emoji

        binding.root.setOnClickListener {
            onProductClick(product)
        }
    }

    override fun getItemCount(): Int = productList.size

    fun updateData(newList: List<ProductModel>) {
        productList = newList
        notifyDataSetChanged()
    }
}
