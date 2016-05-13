using UnityEngine;
using System.Collections;

public class RobotScript : MonoBehaviour {
    Animator anim;
    // Use this for initialization
    void Start() {
        anim = GetComponent<Animator>();
    }

    // Update is called once per frame
    void Update() {

    }

    public void MoveRobot()
    {
        Invoke("Moo", 14.0f);
    }

    void Moo()
    {
        anim.SetTrigger("New Trigger");
        anim.SetTrigger("RobotRight");
        anim.SetTrigger("RobotTarget");
    }
}
