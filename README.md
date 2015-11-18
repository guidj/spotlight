#UIMA: Annotating Movie Reviews

Using UIMA to annotate names of cinematographers, actors and characters in movie reviews


##Data Set
Movie review data set by Bo Pang and Lillian Lee. Can be downloaded [here](http://www.cs.cornell.edu/People/pabo/movie-review-data/)

Download the data files, and place the positive review files under the folder:
```
resources/data/text/pos/
```


##Running

You need to have UIMA [properly set up in your environment](https://uima.apache.org/d/uimaj-current/overview_and_setup.html#ugr.ovv.eclipse_setup).


Use use the documentAnalyser tool to annotate documents:
```
$UIMA_HOME/bin/documentAnalyzer.sh
```

And the annotationViewer to view the annotations:
```
$UIMA_HOME/bin/annotationViewer.sh
```

