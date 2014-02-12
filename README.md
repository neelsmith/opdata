# Open Paleography data #

This is a private repository organizing some paleographic observations in the format that will be used for the Open Paleography project.



## File format ##

`csv` format, in five columns.  Note that order of columns is significant, since our automated processing assumes data will follow this pattern.  Note also that in `csv` format, any columns of data including commas in their data values must be protected by surrounding them in quotation marks.  This will always apply to image references with RoI, and may also apply to free text commentary.

1. URN for observation.  This should be a CITE URN for the observation.  The collection will be  identified as `urn:cite:openpal.TEAM` where `TEAM` is a value assigned to a team of contributors by one of the Open Paleography directors.  Within that collection, team members should use unique numbers to identify individual obervations.  Example:  if we assign `team1` to a group, their first observation could be `urn:cite:openpal:team1.1`.
2. URN for text passage. This should be a CTS URN, including a numerically indexed substring identifying what is captured in the observation.  Example: `urn:cts:greekLit:tlg0012.tlg001.msA:2.4@τ[1]`  means "first occurrence of the letter tau in passage 2.4 of the Venetus A manuscript of the *Iliad*".  The indexed substring should identify precisely what is legible in the text, e.g., cite abbreviated forms, not their expansion.
3. Reading of text passage.  In many cases, this will be no more than the indexed substring (e.g., in the preceding example it would simply be τ), but the reading might also be an expansion of a an abbrevation.
4. Image reference.  Image + RoI, created with Image Citation Tool.
5. Comments.  Free text.

Example line from `csv` file:

    urn:cite:openpal:team1.1,urn:cts:greekLit:tlg0012.tlg001.msA:2.4@τ[1],τ,"urn:cite:hmt:vaimg.VA024RN-0025@0.1702,0.5522,0.025,0.021",tau

