using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class engine : MonoBehaviour {
    
    public void Beginning()
    {
        GameObject gmb = GameObject.Find("StartButton");
        Destroy(gmb);
        for (int i = 1; i < 26; i++)
        {
            Button button = GameObject.Find(i.ToString()).GetComponent<Button>();
            button.interactable = true;
        }
        Button btn1 = GameObject.Find("Exit").GetComponent<Button>();
        Button btn2 = GameObject.Find("Restart").GetComponent<Button>();
        btn1.interactable = true;
        btn2.interactable = true;
        for (int i = 1; i < 26; i++)
        {
            Button button = GameObject.Find(i.ToString()).GetComponent<Button>();
            if (button.image.color == Color.red)
            {
                button.image.color = Color.white;
            }
        }

        this.gameObject.GetComponent<levelHandler>().loadLevel(4);
    }

   public void Exit()
    {
        GameObject gmb = GameObject.Find("GameObject");
        gmb.GetComponent<NewBehaviourScript>().flag(0,"Red");
        gmb.GetComponent<NewBehaviourScript>().OpenTheDoorSet("Close");
        Time.timeScale = 1;
        Destroy(gmb);
    }

	public void Clean()
	{
        for (int i=1;i<26;i++)
        {
            Button button = GameObject.Find(i.ToString()).GetComponent<Button>();
            if (button.image.color == Color.red)
            {
                button.image.color = Color.white;
            }
        }

        this.gameObject.GetComponent<levelHandler>().loadLevel(4);
    }
	// Update is called once per frame
	
}
