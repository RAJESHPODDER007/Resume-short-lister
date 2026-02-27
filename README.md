Job Controller-

Here user can enter job details, which store in the dd in string format and skill set in vector embedding.

Resume Controller-

Here candidate can upload resume in PDF file format, BE program read the PDF file extract the skills, experience, contact details and name.
After that embedding applies on skill and so Cosine similarity search in job vector table.

Based on the minimum distance it gives the result and sorted accordingly based on distance.

Result gives in json format, right now program is sending jobId for the best job match, but it can be configurable.
