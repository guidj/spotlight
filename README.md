#UIMA: Annotating Movie Reviews

Using UIMA to annotate names of cinematographers, actors and characters in movie reviews


##Data Set
Movie review data set by Bo Pang and Lillian Lee. Can be downloaded [here](http://www.cs.cornell.edu/People/pabo/movie-review-data/)

Download the data files, and place the positive review files under the folder:
```
resources/data/text/pos/
```

##Annotation

To execute the annotators with UIMA tools, one needs to configure a few variables regarding the location or descriptor files and other resources.

  - modelMEFilePath: location of the OpenNLP POS tag pack for English (EN)

  - sentenceMEFilePath: location of the OpenNLP sentence identification pack for English (EN)
  
 You can edit these variables in the [PersonDescriptor.xml](descriptors/PersonDescriptor.xml) file.


###Running

You need to have UIMA [properly set up in your environment](https://uima.apache.org/d/uimaj-current/overview_and_setup.html#ugr.ovv.eclipse_setup).


Use use the documentAnalyser tool to annotate documents:
```
$UIMA_HOME/bin/documentAnalyzer.sh
```

And the annotationViewer to view the annotations:
```
$UIMA_HOME/bin/annotationViewer.sh
```

