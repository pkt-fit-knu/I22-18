﻿using UnityEngine;
using System.Collections;

public class EmptyScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    void OnTriggerEnter(Collider col)
    {
        if(col.gameObject.tag=="Player")
        {
            Debug.Log("Here is I am");
            GameObject mp = GameObject.Find("Sound");
            mp.GetComponent<KnockScript>().PlaySound();
        }
    }
}
