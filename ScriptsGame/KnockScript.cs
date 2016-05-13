using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class KnockScript : MonoBehaviour {

	// Use this for initialization
	void Start () {

        //GetComponent<AudioSource>().Play();
    }
	
    void OnTriggerEnter(Collider col)
    {
        InputField field = GameObject.Find("InputField").GetComponent<InputField>();
        Color c = new Color32(28, 29, 44, 56);
        field.image.color = c;
        field.text = "Press <E>";
    }

    void OnTriggerStay(Collider col)
    {
        
        if (col.gameObject.tag=="Player" && Input.GetKeyDown(KeyCode.E))
        {
            InputField field = GameObject.Find("InputField").GetComponent<InputField>();
            Color c1 = new Color32(28, 29, 44, 0);
            field.image.color = c1;
            field.text = "";
            PlaySound();
        }
    }

    void OnTriggerExit(Collider col)
    {
        InputField field1 = GameObject.Find("InputField").GetComponent<InputField>();
        Color c1 = new Color32(28, 29, 44, 0);
        field1.image.color = c1;
        field1.text = "";
    }

	// Update is called once per frame
	void Update () {
	
	}

    public void PlaySound()
    {
        GetComponent<AudioSource>().Play();
    }
}
