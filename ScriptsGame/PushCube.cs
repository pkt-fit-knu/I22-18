using UnityEngine;
using System.Collections;

public class PushCube : MonoBehaviour {
    public float pushStrength = 6.0f;
    void OnControllerColliderHit(ControllerColliderHit hit)
    {
        Rigidbody body = hit.collider.attachedRigidbody;
        if(body == null || body.isKinematic)
        {
            return;
        }
        Vector3 direction = new Vector3(hit.moveDirection.x, 0, hit.moveDirection.z);
        body.velocity = direction * pushStrength;

    }
 }   