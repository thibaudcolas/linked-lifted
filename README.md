# Linked-lifted : Interface revamping for interconnection modules

Linked-lifted hosts code and tools used while creating new interfaces for linked data tools. 
[Datalift](http://datalift.org/) is a linked data publishing platform where those tools — modules — are used.

## [Linked-lifted GitHub Page](http://thibweb.github.com/linked-lifted/)

## tl;dr ##

This module is an interface for [StringToURI]("http://stringtouri.assembla.me/").  
Version 0.9

## What is StringToURI ? ##

StringToURI is a simple interconnection module which converts Strings to URIs _(hence the name)_.
StringToURI takes two predicates and matches their objects' values. When the values are equal, the object (a string value) of the triple to be modified is replaced by the subject of the reference triple (a URI).

------------------------------------------------------------------------------

### An abstract example ###

Let's define two triples : S, P, O and SS, PP, OO.

* SPO is a triple to update, where O is a string value.
* SSPPOO is our reference triple where OO is also a string.

If OO and O are found equal, the first triple will become S, P, SS and the second one will remain the same.

------------------------------------------------------------------------------

### A real life fictional example ###

In a given dataset, an event takes place in a city.

    <givenDataset:AwesomeMusicFestival> . <givenDataset:takesPlaceIn> . "Acapulco"
    
It'd be better the AwesomeMusicFestival was directly linked to the entity Acapulco, not only to a string describing it.
Let's find a reference dataset which contains the entity Acapulco.

    <referenceDataset:Acapulco> . <rdf:type> . <referenceDataset:City>
    <referenceDataset:Acapulco> . <referenceDataset:name> "Acapulco"
    ...
    
We can compare the two predicates `<givenDataset:takesPlaceIn>` and `<referenceDataset:name>` to obtain :

    <givenDataset:AwesomeMusicFestival> . <givenDataset:takesPlaceIn> . <referenceDataset:Acapulco>
    
## External resources ##

* StringToURI was made in Java using the OpenRDF Sesame API and RDF repositories.
* [StringToURI documentation]("http://stringtouri.assembla.me/")
* [StringToURI interface design and development]("http://thibweb.github.com/linked-lifted/")
