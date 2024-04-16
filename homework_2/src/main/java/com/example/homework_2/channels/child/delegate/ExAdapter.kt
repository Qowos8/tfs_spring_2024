import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_2.R
import com.example.homework_2.channels.AllStreamItem
import com.example.homework_2.channels.TopicItem
import com.example.homework_2.databinding.ExpandableChildBinding
import com.example.homework_2.databinding.ExpandableParentBinding

class ExAdapter(
    private val streamItemClick: (AllStreamItem) -> Unit,
    private val topicItemClick: (TopicItem) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_STREAM = 1
    private val VIEW_TYPE_TOPIC = 2

    private val items: MutableList<Any> = mutableListOf()
    private var openedStreamId: Int? = null

    private val topicsMap: MutableMap<Int, List<TopicItem>> = mutableMapOf()
    private var streams: List<AllStreamItem> = emptyList()
    private var expandedStreamPosition = RecyclerView.NO_POSITION


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_STREAM -> StreamViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.expandable_parent, parent, false)
            )
            VIEW_TYPE_TOPIC -> TopicViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.expandable_child, parent, false)
            )
            else -> throw IllegalArgumentException("Unknown view type: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is StreamViewHolder -> {
                val streamItem = streams[holder.adapterPosition]
                holder.bind(streamItem)
                holder.itemView.setOnClickListener {
                    streamItemClick.invoke(streamItem)
                    expandedStreamPosition = holder.adapterPosition
                    notifyDataSetChanged()
                }
            }
            is TopicViewHolder -> {
                val topicItem = topicsMap[streams[expandedStreamPosition].id]?.get(position)
                if (topicItem != null) {
                    holder.bind(topicItem)
                    holder.itemView.setOnClickListener {
                        topicItemClick.invoke(topicItem)
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return if (streams.isNotEmpty()) {
            if (expandedStreamPosition != RecyclerView.NO_POSITION) {
                topicsMap[streams[expandedStreamPosition].id]?.size ?: 0
            } else {
                0
            }
        } else {
            0
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == expandedStreamPosition) {
            VIEW_TYPE_TOPIC
        } else {
            VIEW_TYPE_STREAM
        }
    }

    fun submitStreams(streams: List<AllStreamItem>) {
        //items.clear()
        items.addAll(streams)
        notifyDataSetChanged()
    }

    fun updateTopics(streamId: Int, topics: List<TopicItem>) {
        topicsMap[streamId] = topics
        notifyItemRangeChanged(0, itemCount)
    }

    inner class StreamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ExpandableParentBinding.bind(itemView)

        fun bind(streamItem: AllStreamItem) {
            binding.apply {
                nameStream.text = streamItem.name

                root.setOnClickListener{
                    streamItemClick(streamItem)
                }
            }
        }
    }

    inner class TopicViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ExpandableChildBinding.bind(itemView)

        fun bind(topicItem: TopicItem) {
            binding.apply {
                messageCount.text = topicItem.messageCount.toString()
                nameTopic.text = topicItem.name

                root.setOnClickListener {
                    topicItemClick(topicItem)
                }
            }
        }
    }
}
