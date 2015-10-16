Availability example 1
======================

This is the first example for the problem of availability.

It is a simplified implementation of rendering candlesticks in JFreeChart framework. Upon each call to CandleStick's drawItem method, the method iterates through all objects within the data series for computation. In this program, the *computation of CandleStick's position* is simulated by averaging the all the other objects' positions. For all the other objects, their positions are simply returned as 0.