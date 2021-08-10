using UnityEngine;
using System.Collections;

public class BlockBreakOneHit : MonoBehaviour {

	public void OnTriggerEnter2D(Collider2D trigger){
		print ("Trigger");
	}
	void OnCollisionEnter2D(Collision2D collision){
		print ("Collider");
	}
}
