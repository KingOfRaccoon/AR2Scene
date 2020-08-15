package com.unity3d.player

import android.app.Fragment
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast

class UnityFragment : Fragment() {
    protected var mUnityPlayer // don't change the name of this variable; referenced from native code
            : UnityPlayer? = null

    //Declare a FrameLayout object


    //Test Button
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        mUnityPlayer = UnityPlayer(activity)

        val view: View = inflater.inflate(R.layout.fragment_unity, container, false)
        //Inflate the frame layout from XML
        var fl_forUnity = view.findViewById<FrameLayout>(R.id.fl_forUnity)
        //Add the mUnityPlayer view to the FrameLayout, and set it to fill all the area available
        fl_forUnity.addView(mUnityPlayer!!.view, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        var button = view.findViewById<Button>(R.id.button)
//        UnityPlayer.UnitySendMessage()
        button.setOnClickListener {
            Toast.makeText(view.context, "BOOM!", Toast.LENGTH_SHORT).show()
            UnityPlayer.UnitySendMessage("ggf", "startChange", null)
        }
        //Requesting the Focus
        mUnityPlayer!!.requestFocus()
        //The main fix of resolving BLACK SCREEN PLAYER ISSUE
        mUnityPlayer!!.windowFocusChanged(true) //First fix Line
        // Yes, it's "static" way and should to be more dynamic, anyway, it works well
        return view
    }

    // Quit Unity
    override fun onDestroy() {
        mUnityPlayer!!.quit()
        super.onDestroy()
    }

    // Pause Unity
    override fun onPause() {
        super.onPause()
        mUnityPlayer!!.pause()
    }

    // Resume Unity
    override fun onResume() {
        super.onResume()
        mUnityPlayer!!.resume()
    } // This ensures the layout will be correct.

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        mUnityPlayer!!.configurationChanged(newConfig)
    }
}
