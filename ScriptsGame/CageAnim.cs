using UnityEngine;
using UnityEngine.UI;
using System.Collections;

public class CageAnim : MonoBehaviour {

	bool doorIsOpened;
	Animator anim;

	// Use this for initialization
	void Start () {
		doorIsOpened = false;
		anim = GetComponent<Animator>();
	}
	
	// Update is called once per frame
	void Update () {
	
	}
    void OnTriggerEnter()
    {
        GameObject gmb = GameObject.Find("Player");
        InputField field = GameObject.Find("InputField").GetComponent<InputField>();
        Color c = new Color32(28, 29, 44, 56);
        field.image.color = c;
        field.text = "Press <E>";
    }

	void OnTriggerStay (Collider col)
	{
        GameObject gmb = GameObject.Find("Player");
        if (col.gameObject.tag == "Player" && Input.GetKeyDown(KeyCode.E))
		{
            InputField field = GameObject.Find("InputField").GetComponent<InputField>();
            Color c = new Color32(28, 29, 44, 0);
            field.image.color = c;
            field.text = "";
            doorIsOpened = true;
			anim.SetTrigger("Open");
}
        if(doorIsOpened==true)
        {
            gmb.GetComponent<FirstPersonController>().M();
           // gmb.GetComponent<FirstPersonController>().l();
            //.transform.position = new Vector3();

            //doorIsOpened = false;
        }
     
    }

    void OnTriggerExit(Collider col)
    {
        InputField field = GameObject.Find("InputField").GetComponent<InputField>();
        Color c = new Color32(28, 29, 44, 0);
        field.image.color = c;
        field.text = "";
    }
}
