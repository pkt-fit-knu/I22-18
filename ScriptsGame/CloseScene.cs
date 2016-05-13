using UnityEngine;
using System.Collections;

public class CloseScene : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	public void Delete()
    {
        GameObject gmb = GameObject.Find("Artefact");
        Time.timeScale = 1;
        GameObject tmp = GameObject.Find("GameObject1");
        tmp.GetComponent<NewBehaviourScript>().fBook(0);
        Destroy(gmb);
    }
	// Update is called once per frame
	void Update () {
	
	}
}
