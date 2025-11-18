package com.example.halloween

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlin.getValue

class StoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_story, container, false)
    }

    //recóllense os argumentos
    private val args: StoryFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
            //obtéñense os elementos visuais que se mostrarán e onde se colocaran os datos
            val textTitle = view.findViewById<TextView>(R.id.text_title)
            val imageStory = view.findViewById<ImageView>(R.id.image_story)
            val textShow = view.findViewById<TextView>(R.id.text_show)
            val buttonVolver = view.findViewById<Button>(R.id.button_volver)

            val theme = args.themeSelected

            //Mostra o título
            textTitle.text = theme

            //Mostra a imaxe segundo o tema
            val imageRes = when (theme){
                "Casa encantada" -> R.drawable.casa
                "Bosque maldito" -> R.drawable.bosque
                "Aquelarre de bruxas" -> R.drawable.bruxas
                else -> R.drawable.samain
            }
            //Cárgase a imaxe (identificada con imageRes) e asignase ao compoñente da interface de usuario chamado imageStory para que se mostre en pantalla
            imageStory.setImageResource(imageRes)

            // Xerar o conto personalizado. Chámase a generateStory() para crear un texto personalizado co nome de usuario e o tema e asignase ao TextView que o mostrará
            val story = generateStory(args.userName, args.themeSelected)
            textShow.text = story

            buttonVolver.setOnClickListener {
                findNavController().navigate(R.id.action_storyFragment_to_nameFragment)
            }
    }

        // esta función recibe os argumentos de nome e temática e xera o valor que vai devolver en función diso
        private fun generateStory(name: String, theme: String): String {
            return when (theme) {
                "Casa encantada" -> "Había unha vez unha persoa chamada $name, que se aventurou nunha casa encantada chea de fantasmas. Atreveuse a cruzar o portal de ferro oxidado daquela vella mansión, onde o silencio era máis pesado que a néboa. Escoitou un susurro frío no corredor principal. A luz do seu candil revelou que non estaba soa; os ollos dos retratos antigos seguíana dende as paredes"
                "Bosque maldito" -> "Nun oscuro bosque, $name escoitou susurros e viu luces estrañas entre as árbores, coma ollos de almas perdidas. A curiosidade venceu ao temor, e seguiu as luces que bailaban no camiño ata un muro de pedras antigas. Alí, a luz mais grande amosoulle que iso eran as almas da Santa Compaña, que pasaban de noite para dicir adeus"
                "Aquelarre de bruxas" -> "A noite era fría cando $name entrou nunha mansión misteriosa onde nada era o que parecía e o aire cheiraba a herbas e fume. No salón principal, as bruxas xa estaban xuntas, facendo un círculo arredor dunha fogueira pequena. Entón, a bruxa máis vella deulle a man e dixo: ''agora es nosa''. E así comezou o rito de iniciación "
                else -> "$name viveu unha noite de defuntos inolvidable."
            }
        }
}