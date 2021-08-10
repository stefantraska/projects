using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class Slider : MonoBehaviour {

	private static float sliderValue;
	public GameObject slide;
	
	void Start(){
		slide.GetComponent<UnityEngine.UI.Slider>().value = sliderValue;
	}
	
	public void ValueChange(){
		sliderValue = slide.GetComponent<UnityEngine.UI.Slider>().value;
		AudioListener.volume = sliderValue;
	}
}
