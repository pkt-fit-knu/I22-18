using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class changingTheColor : MonoBehaviour {
    public Button myButton;
	// Use this for initialization
	void Start () {
	
	}

    // Update is called once per frame
   
        void Update()
    {
        int fl = 0;
        for (int i = 1; i < 26; i++)
        {
            Button button = GameObject.Find(i.ToString()).GetComponent<Button>();
            if (button.image.color == Color.white)
            {
            }
            else
            {
                fl = 1;
            }
        }
        if (fl == 0)
        {
            
            Time.timeScale = 1;
            GameObject gmb = GameObject.Find("GameObject");
            gmb.GetComponent<NewBehaviourScript>().flag(1,"Green");
            gmb.GetComponent<NewBehaviourScript>().OpenTheDoorSet("Open");
            Destroy(gmb);
        }
    }


    void makeMove(int name)
    {

        turn(name);
        turn(name + 5);
        turn(name - 5);
        if (name % 5 != 0)
        {
            turn(name + 1);
        }
        if (name % 5 != 1)
        {
            turn(name - 1);
        }

    }

    void turn(int name)
    {
        if (name < 1 || name > 25) return;
        else
        {
            newColor(name);
        }

        

    }

    void newColor(int data)
    {
        Button button = GameObject.Find("" + data).GetComponent<Button>();
        if (button.image.color != Color.red)
        {
            var color = button.image.color;
            color = Color.red;
            button.image.color = color;

        }
        else
        {
            button.image.color = Color.white;
        }
    }

    public void press () {
        
        makeMove(int.Parse(myButton.name));
	}

    public void press2(int n)
    {
        Button tmp = GameObject.Find(n.ToString()).GetComponent<Button>();
        if (tmp.image.color == Color.red)
        { tmp.image.color = Color.white; }
        else
        {
            tmp.image.color = Color.red;
        }
    }
   
}
