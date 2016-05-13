using UnityEngine;
using System.Collections;

public class NewBehaviourScript : MonoBehaviour {
    static int fl;
    static int flBook;
    Material mat;
    static string stan;
	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
	
	}

    public void OpenTheDoorSet(string s)
    {
        stan = s;
    }

    public bool openTheDoor()
    {
        if (stan == "Open")
            return true;
        else
            return false;
    }

    public void flag(int n,string color)
    {
        fl = n;
        mat=Resources.Load(color, typeof(Material)) as Material;
        GameObject gmb = GameObject.Find("Sphere");
        GameObject gmb1 = GameObject.Find("Sphere1");
        gmb.GetComponent<Renderer>().material = mat;
        gmb1.GetComponent<Renderer>().material = mat;
    }

    public void fBook(int n)
    {
        flBook = n;
    }
    public bool flagBook()
    {
        if(flBook==1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public bool flagBool()
    {
        if (fl == 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
}
