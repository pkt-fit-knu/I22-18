using UnityEngine;
using System.Collections;

public class CameraSound : MonoBehaviour {
    public AudioClip openClose;
    AudioSource audio;
    public AudioClip block;
    // Use this for initialization
    void Start () {
	 audio = GetComponent<AudioSource>();
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    public void BlockDoor()
    {
        audio.clip = block;
        audio.Play();
    }
    public void openCloseDoor()
    {
        audio.clip = openClose;
        audio.Play();
    }
}
