package com.example.eim_coursepack

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import androidx.databinding.DataBindingUtil

import com.example.eim_coursepack.databinding.FragmentAllVideosBinding


class AllVideos : Fragment() {
    lateinit var Videoname: VideoTitle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAllVideosBinding>(inflater,R.layout.fragment_all_videos,container,false)
        binding.video=this

        val args = AllVideosArgs.fromBundle(requireArguments())

    // Default video
        var offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample1}")
        Videoname = VideoTitle("Atom And Ohms Law ")

        when (args.videoFlag) {
            "FirstVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample1}")
                Videoname = VideoTitle("Atom And Ohms Law")
            }
            "SecondVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample2}")
                Videoname = VideoTitle("Sources Of Energy")
            }
            "ThirdVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample3}")
                Videoname= VideoTitle("Electrical Tools And Energy")
            }
            // Unit 2
            "FourthVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample4}")
                Videoname= VideoTitle("Different Types Of Forms")
            }
            "FifthVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample5}")
                Videoname= VideoTitle("English And Metric System Of Measurement")
            }
            "SixthVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample6}")
                Videoname= VideoTitle("Ohm’S Law ")
            }
            "SeventhVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample7}")
                Videoname= VideoTitle("Multimeter")
            }
            "EighthVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample8}")
                Videoname= VideoTitle("Electrical Symbols")
            }
            "NinthVid" -> {
                offlineUri = Uri.parse("android.resource://"+activity?.packageName+"/${R.raw.sample9}")
                Videoname= VideoTitle("Basic Maintenance")
            }
        }

        val watch = binding.videoView
        val mediaController = MediaController(activity)
        mediaController.setAnchorView(watch)
        watch.setMediaController(mediaController)
        watch.setVideoURI(offlineUri)
        watch.requestFocus()
        watch.start()

        return binding.root
    }

}

