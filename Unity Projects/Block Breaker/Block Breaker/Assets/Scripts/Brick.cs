using UnityEngine;
using System.Collections;

public class Brick : MonoBehaviour {

	public AudioClip crack;
	public int timesHit;
	private LevelManager levelManager;
	public Sprite[] hitSprites;
	public static int brickCount = 0;
	public GameObject particle;
	bool isBreakabale;

	// Use this for initialization
	void Start () {
		isBreakabale = (this.tag == "Breakable");
		if(isBreakabale){
			brickCount++;
		}
		timesHit = 0;
		levelManager = GameObject.FindObjectOfType<LevelManager>();
	}
	
	// Update is called once per frame
	void Update () {
	
	}
	
	void OnCollisionEnter2D (Collision2D col){
		if(isBreakabale){
			HandleHits();
		}
	}
	
	void HandleHits(){
		timesHit++;
		int maxHits = hitSprites.Length + 1;
		if(timesHit >= maxHits){
			AudioSource.PlayClipAtPoint(crack, this.transform.position, 0.5f);
			brickCount--;
			levelManager.BrickDestroyed();
			PuffSmoke();
			Destroy(gameObject);
		}else{
			LoadSprites();
		}
	}
	
	void PuffSmoke(){
		GameObject smokePuff = Instantiate(particle, transform.position, Quaternion.identity) as GameObject;
		smokePuff.GetComponent<ParticleSystem>().startColor = gameObject.GetComponent<SpriteRenderer>().color;
	}
	
	void LoadSprites(){
		int spriteIndex = timesHit - 1;
		if(hitSprites[spriteIndex] != null){
			this.GetComponent<SpriteRenderer>().sprite = hitSprites[spriteIndex];
		}else{
			Debug.LogError("Brick sprite missing");
		}
	}
}
